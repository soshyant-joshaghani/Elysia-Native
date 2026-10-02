# Development

```bat
__ctrl__\elysia-native-ctrl.bat web use svelte
__ctrl__\elysia-native-ctrl.bat setup-local
__ctrl__\elysia-native-ctrl.bat dev run all
```

| Surface | URL |
|---------|-----|
| Dashboard | http://dashboard.localhost |
| API docs | http://api.localhost/docs |
| Scalar | http://api.localhost/sdoc |
| Direct API | http://localhost:8000/docs |
| Vite | http://localhost:5000 |

The API runs with `bun run --watch src/main.ts` in `backend/`; the worker with `bun run --watch src/jobs/workers/index.ts`; Vite from `frontend/web`. `dev run all --slim` skips Redis and the worker. Only one Traefik stack can bind port 80: stop the other proxy, or run `dev run apps` and use the direct URLs.
