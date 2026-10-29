# Dashboard UI

Tailwind and shadcn-svelte. The Svelte site is installed into `frontend/web` with `web use svelte`. It is a copy of Elysia-Svelte's frontend, which is Fast-Svelte's UI.

The notes page (`/sample/notes`) is the canonical screen. Keep the route and the module name when moving a frontend between FoxG families. The API client is plain `fetch` against `/api/v1`, so it works unchanged against any backend that follows the contract. The copy has no dependency on this backend's types.
