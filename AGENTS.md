# AGENTS.md

Elysia-Native consumes the same API as Elysia-Svelte. Do not invent a second server style here, and do not copy Hono or FastAPI modules into the native shells.

- Backend rules are the ones in [../elysia-svelte/AGENTS.md](../elysia-svelte/AGENTS.md): Route → Service → Repository (Drizzle), `{"detail": "..."}` errors, Redis list jobs, no queue library. The wire format is [CONTRACT.md](../../../CONTRACT.md).
- `backend/` here is a copy of Elysia-Svelte's. Change both together.
- `web use svelte` is the way to fill `frontend/web`. The copy is plain `fetch`; do not add Eden or backend imports to it.
- WinUI and Compose are not built yet. When they are, they call the paths in `frontend/client-routes.json` (`/`, `/login`, `/sample/notes`, `/admin`).
