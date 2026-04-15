# Verse Flow Backend Tasks

This document contains a structured list of tasks required to build the Verse Flow Backend service. Each task is designed to be highly self-contained with clear execution steps so that any autonomous agent (or human) can pick up a task and proceed without ambiguity.

---

## 1. Project Initialization & Scaffolding

This phase focuses on generating the core Spring Boot 3.2 project structure and installing necessary tools.

- [ ] **Install Spring Boot CLI:** Ensure the Spring Boot CLI is installed on this Mac via Homebrew. Run the following commands:
  ```bash
  brew tap spring-io/tap
  brew install spring-boot
  ```
- [ ] **Scaffold Spring Boot Project:** Run the following command in the `verse_flow_service` root directory to scaffold the initial project structure in place:
  ```bash
  spring init --build=maven \
    --java-version=21 \
    --dependencies=web,data-jpa,postgresql,validation,cloud-gcp-storage \
    --group=com.verseflow \
    --artifact=verse-flow-service \
    --name=verse-flow-service \
    --package-name=com.verseflow.service \
    --extract .
  ```
- [ ] **Add OpenAPI Swagger Dependency:** Update the `pom.xml` to include `springdoc-openapi-starter-webmvc-ui` (version compatible with Spring Boot 3) to enable the Swagger API documentation on boot.
- [ ] **Test App Startup Context:** Execute `./mvnw spring-boot:run` to ensure the clean project context starts up properly (ignoring database connection errors at this stage if unconfigured).

---

## 2. Database and Data Models

This section sets up the PostgreSQL configuration and necessary application data transfer objects (DTOs).

- [ ] **Configure PostgreSQL Properties:** Update `src/main/resources/application.properties` (or `application.yml`) to point to a PostgreSQL 16 database. Assign the connection `url`, `username`, and `password` properties.
- [ ] **Implement Database Schema:** Add a schema initialization script (e.g., using Flyway/Liquibase or manual SQL) or JPA `@Entity` setup for the `voice_over_metadata` table, defining:
  - `id` (VARCHAR 255, PK)
  - `text` (TEXT, NOT NULL)
  - `language` (VARCHAR 10, NOT NULL)
  - `storage_path` (VARCHAR 512, NOT NULL)
  - `duration` (INTEGER)
  - `created_at` (TIMESTAMP)
  - `last_accessed` (TIMESTAMP)
- [ ] **Create DTOs for VoiceOver:** Create `VoiceOverRequest` and `VoiceOverResponse` classes modeling the JSON structures explicitly outlined in the requirements.
- [ ] **Create DTOs for Quran Content:** Create standard POJO representations for `Surah`, `Verse`, and `Translation`.

---

## 3. Core Provider Interfaces & Implementations

Build the external API integration layer using dedicated providers.

- [ ] **Define Provider Interfaces:** Map out `QuranContentProvider` and `VoiceGenerationProvider` interfaces according to the interface signatures given in the requirements documentation.
- [ ] **Implement AlQuranCloudProvider:** Provide a concrete implementation of `QuranContentProvider`. It should use standard HTTP clients (such as Spring's `RestClient` or `RestTemplate`) to communicate with the `Al Quran Cloud API` to fetch Surah listings, verses, translations, and audio definitions.
- [ ] **Implement GoogleTextToSpeechProvider:** Provide a concrete implementation of `VoiceGenerationProvider`. Include dependencies for Google Cloud Text-to-Speech and leverage GCP standard libraries to fetch `TextToSpeechClient` configurations for generating audio streams.

---

## 4. Google Cloud Storage Integration

This section implements cloud bucket storage and fetching logic for generated TTS voice-overs.

- [ ] **Configure GCP Storage Initialization:** Add your credentials properties to load default Google Application Credentials into the `Storage` or Google Cloud Storage Spring Bean.
- [ ] **Implement Audio Storage Handlers:** Create a service responsible for saving audio streams to the `verse-flow-audio` GCS bucket, returning and verifying signed URLs.
- [ ] **Implement Path System:** Implement logic mapping ensuring generated assets strictly stick to the `{content-type}/{language}/{unique-id}.mp3` path layout requirements.
- [ ] **Implement VoiceOver Flow Strategy:** Wire together the VoiceOver caching flow: Query DB by `uniqueId` -> If exists, return Cloud Storage Signed URL -> If not exists, use GCP to generate new voiceover -> Store in GCS -> Persist metadata in DB.

---

## 5. API Controllers

Expose endpoints for external clients connecting over HTTP.

- [ ] **Create QuranContentController:** Implement a `@RestController` mapped to `/api/v1/quran`. Wire up the provider to answer GET endpoints for `/surahs`, `/surah/{surahNumber}`, `/verses/{surahNumber}/{verseNumber}`, and `/translations/{surahNumber}/{verseNumber}`.
- [ ] **Create RecitationController:** Implement a `@RestController` mapped to `/api/v1/recitations`. Include GET endpoints for `/reciters`, `/audio/{reciterId}/{surahNumber}/{verseNumber}`, and POST/DELETE endpoints mapping to offline verse downloads.
- [ ] **Create VoiceOverController:** Implement a `@RestController` mapped to `/api/v1/voiceover`. Add POST `/generate` consuming a `VoiceOverRequest`, GET `/audio/{voiceoverId}`, and DELETE `/audio/{voiceoverId}` endpoints.

---

## 6. Global Error Handling & Security

Handle cross-cutting API concerns and response formatting.

- [ ] **Create Global Exception Handler:** Create a `@ControllerAdvice` annotated class. Catch 400 Bad Request, 404 Not Found, and 500 Internal Server errors to return the standard, typed JSON Error Response object defined in the requirements.
- [ ] **API Security Configuration:** Set up basic API key / rate-limiting configuration and properly restrict CORS according to basic Spring Security configuration.
- [ ] **Configure Request/Response Logging:** Set up system or web filters monitoring request payload footprints, HTTP status codes, and execution timestamps.
