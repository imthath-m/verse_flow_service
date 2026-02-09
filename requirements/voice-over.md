# Voice Over Service Requirements

## Overview
A Spring Boot based service that converts text to speech (voice-over) in multiple languages using a third-party service, with caching capabilities to optimize costs and performance.

## Functional Requirements

### 1. Text to Speech Conversion
- Accept requests with the following parameters:
  - `textId` (string): Unique identifier for the text content
  - `text` (string): The actual text content to convert
  - `language` (string): Language code of the text (e.g., 'en-US', 'es-ES')
  - Optional parameters for voice customization (to be determined based on chosen 3P service)

### 2. Voice Over Storage
- Store generated audio files in Google Cloud Storage
  - Use regional storage for better performance
  - Implement lifecycle policies for cost optimization
- Storage structure:
  - Bucket: `verse-flow-voiceovers`
  - Path format: `{environment}/{textId}/{language}.{format}`
  - Example: `prod/abc123/en-US.mp3`

### 3. Caching and Retrieval
- Before generating new voice-over:
  - Check if audio file exists for given textId and language
  - If exists, return the stored audio file URL/content
  - If not exists, proceed with generation
- Maintain metadata in database:
  - textId
  - language
  - storage path
  - creation timestamp
  - file format
  - file size
  - status (processing/completed/error)

## Technical Requirements

### 1. API Endpoints

#### Generate/Retrieve Voice Over
```
POST /api/v1/voice-over
Content-Type: application/json

{
    "textId": "unique-text-identifier",
    "text": "Text content to convert",
    "language": "en-US"
}

Response:
{
    "textId": "unique-text-identifier",
    "status": "completed",
    "audioUrl": "https://storage.googleapis.com/...",
    "format": "mp3",
    "generated": false  // indicates if newly generated or served from cache
}
```

### 2. Third Party Integration
- Implement abstract interface for text-to-speech service:
```java
public interface TextToSpeechService {
    AudioContent generateSpeech(String text, String language);
}
```
- Implementation should be easily swappable with minimal code changes
- Potential services to consider:
  - Google Cloud Text-to-Speech
  - Amazon Polly
  - Microsoft Azure Text to Speech

### 3. Storage Requirements
- Use Google Cloud Storage
- Configure appropriate CORS settings for direct URL access
- Implement automatic cleanup for unused files (configurable retention period)
- Enable server-side encryption for stored files

### 4. Performance Requirements
- Maximum processing time: 5 seconds for texts up to 1000 characters
- Support concurrent requests
- Implement rate limiting based on chosen 3P service limits
- Cache frequently accessed files in CDN

### 5. Error Handling
- Implement retry mechanism for 3P service failures
- Store failed attempts in database with error details
- Provide clear error messages in API responses
- Monitor and alert on error thresholds

## Security Requirements

1. Authentication
   - All API endpoints must be secured
   - Use JWT based authentication
   - Implement role-based access control

2. Data Protection
   - Encrypt sensitive data in transit and at rest
   - Implement request validation
   - Set up proper access controls for GCP resources

## Monitoring and Logging

1. Metrics to Track
   - Generation success/failure rates
   - Cache hit/miss ratio
   - Response times
   - Storage usage
   - Cost per request
   - 3P service latency

2. Logging Requirements
   - Request/response details
   - Error stack traces
   - 3P service interaction logs
   - Storage operation logs

## Configuration
- All configurable parameters should be externalized:
  - 3P service credentials
  - Storage bucket details
  - Cache settings
  - Rate limits
  - Retention periods
  - Environment-specific settings

## Future Considerations
1. Support for additional voice customization:
   - Voice gender
   - Speaking rate
   - Pitch
   - Volume

2. Batch processing capabilities for multiple texts

3. Support for additional audio formats

4. Integration with streaming services for real-time voice-over

## Development Guidelines
1. Follow Spring Boot best practices
2. Implement comprehensive unit and integration tests
3. Document all API endpoints using OpenAPI/Swagger
4. Maintain proper version control and branching strategy
5. Set up CI/CD pipeline with proper testing stages
