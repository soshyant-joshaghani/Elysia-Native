[![](./FoxG-Kit.png)](./FoxG-Kit.png)

# Elysia-Native

GitHub: [Elysia-Native](https://github.com/soshyant-joshaghani/Elysia-Native)

**One Elysia API on Bun. A Svelte site you install. Native shells later.**

The starter-tier Elysia kit with a frontend slot. `backend/` is the same API as [Elysia-Svelte](../elysia-svelte/README.md) and follows the [FoxG wire contract](../../../CONTRACT.md). `frontend/web` is empty until you install a site:

```bat
__ctrl__\elysia-native-ctrl.bat web list
__ctrl__\elysia-native-ctrl.bat web use svelte
__ctrl__\elysia-native-ctrl.bat setup-local
__ctrl__\elysia-native-ctrl.bat dev run all
```

`__ctrl__/kits.json` lists `svelte` only, pointing at `../elysia-svelte/frontend`. Elysia-Next and Elysia-Nuxt are not catalog entries yet.

| Command | Effect |
|---------|--------|
| `web list` | Catalog and installed kit |
| `web use svelte` | Copy the frontend into `frontend/web` and rewrite its Dockerfiles for that build context |
| `web use svelte --replace` | Copy again |
| `web use svelte --replace --force` | Replace local edits |
| `web status` | Lock and dirty flag |

The copied frontend calls the API with plain `fetch` and has no dependency on `backend/`, so the copy needs no edits.

Modules match Elysia-Svelte: `backend/src/modules/apps/sample`, `base`, and `system`. The CLI is the Python `__ctrl__` (dev, test, app, prod, remote, `web use`).

| Service | URL |
|---------|-----|
| Dashboard | http://dashboard.localhost |
| API (Swagger) | http://api.localhost/docs |
| API (Scalar) | http://api.localhost/sdoc |
| Direct API | http://localhost:8000/docs |
| Superuser | `admin@example.com` / `Admin@1234` |

Jobs use the contract's Redis list (`foxg:jobs`). Tests: `test backend` (PGlite, no services; `tests/backend/` mirrors the module paths), `test frontend` (`svelte-check` in `frontend/web`), `test contract` (see [tests/README.md](tests/README.md)). Environment names match Fast; copy `.env.example` to `.env`.

Windows (`frontend/win`) and Android (`frontend/android`) are shells that mirror `frontend/client-routes.json`; they are not a WebView of the site.
