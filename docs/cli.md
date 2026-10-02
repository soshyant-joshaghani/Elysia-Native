# CLI

```bat
__ctrl__\elysia-native-ctrl.bat <command>
```

| Command | Effect |
|---------|--------|
| `web list`, `web use svelte`, `web status`, `web update` | Install the Svelte frontend into `frontend/web` |
| `setup-local` | `bun install` in `backend/`, `npm install` in `frontend/web` |
| `dev run all` | Infra, API, worker, Vite |
| `dev stop all` | Stop host apps and compose |
| `test all` | Backend tests and the frontend `svelte-check` |
| `test contract` | Run the wire-contract test against a running API |
| `app create <name>` | Module stub (backend and frontend) |
| `prod start` / `prod stop` | Production compose |
| `logs` | Host and production logs |
| `flatten` / `restore-flat` | Single-root git history |
| `ping`, `clone`, `env`, `start`, `stop`, `status`, `update` | SSH operations from `servers.json` |

Native kits also have `native list`. Linux and macOS use `elysia-native-ctrl.sh`. Details: [`__ctrl__/README.md`](../__ctrl__/README.md).
