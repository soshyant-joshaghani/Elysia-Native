# Frontend

The Svelte site is installed into `frontend/web` with `web use svelte` (a copy of `../elysia-svelte/frontend`). It calls the API with plain `fetch`; there is no Eden client and no import from `backend/`.

Modules are only `base/` and `apps/<name>/`. UI primitives live in `base/ui`. Style with Tailwind utilities.

`client-routes.json` lists the dashboard routes the native shells (`android/`, `win/`) mirror: `/`, `/login`, `/sample/notes`, `/admin`.
