# Progress

| Stage | Status | Note |
|-------|--------|------|
| Kit slot | done | README, `web use`, route names |
| Wire contract | done | Backend is Elysia-Svelte's: CONTRACT.md routes, `detail` errors, Redis list jobs |
| Web install | pending | Run `web use svelte` when a product needs the copied frontend |
| Backend tests | done | `tests/backend/` mirrors module paths; PGlite, in-memory cache and queue; run from `backend/` |
| Live contract run | done | `contract_test.py --local --jobs` against Postgres 18, Redis 8, the API and the worker |
| Windows / Android | pending | Not built |

Last update: 2026-10-01
