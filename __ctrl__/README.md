# elysia-native `__ctrl__`

The **control layer** for Elysia-Native — one CLI for the web kit, native shells, and the project lifecycle.

```bat
elysia-native-ctrl.bat web use svelte
elysia-native-ctrl.bat setup-local
elysia-native-ctrl.bat dev run all
elysia-native-ctrl.bat native run win
elysia-native-ctrl.bat native run android
```

## Command map

| Area | Commands |
|------|----------|
| Web kit | `web list` · `web use {svelte\|next\|nuxt\|rio}` · `web status` · `web update` |
| Native clients | `native list` · `native build {android\|win\|all}` · `native run {android\|win}` · `native clean {android\|win\|all}` |
| Local tooling | `setup-local [--force]` |
| Dev stack | `dev run\|stop\|down\|purge\|reset {infra,apps,all}` · `--slim` for lightweight runtime |
| App scaffold | `app create <name>` |
| Tests | `test {all,backend,frontend,contract}` |
| Local prod smoke | `prod start\|stop\|reset\|backup-acme\|…` |
| SSH / VM | `setup`, `pubkey`, `clone`, `env`, `start`, `stop`, `update`, `reset`, `backup-acme`, `connect`, … |

`web use` downloads that kit's `frontend/` from GitHub into `frontend/web` and writes `frontend/kit.lock.json`. `setup-local`, `dev run`, and `test frontend` stop until a kit is installed. Switching kits needs `web use <kit> --replace`. `web update` re-fetches the locked branch and stops when `frontend/web` has local edits unless you pass `--force`.

## Layout

| Path | Role |
|------|------|
| `kits.json` | Svelte, Next, Nuxt, and Rio download profiles |
| `platforms.json` | Windows and Android build paths |
| `servers.json` | Single VM entry |
| `safe/` | PEM, address, prod `.env` |
| `static/gpg` | Docker Ubuntu GPG (Iran bootstrap) |
| `remote/` | On-VM / local-prod compose scripts |
| `elysia-native-ctrl.bat` / `.sh` | CLI entry |

## Local dev

`dev run` starts the backend on :8000 and the installed web kit on :5000. Svelte and Next use `npm run dev -w frontend`. Nuxt runs inside `frontend/web`. Rio runs `python -m rio run --port 5000 --public`.

Production compose builds `frontend/web` (`context: frontend/web`). `web use` rewrites the extracted Dockerfiles for that context.

## Native clients

```bat
elysia-native-ctrl.bat native list
elysia-native-ctrl.bat native build win
elysia-native-ctrl.bat native run android
```

Windows is `dotnet build` of `FastNative.Client` without an MSIX package, then launch of the exe. Android is `gradlew assembleDebug`, then install and launch when adb sees a device.

## Quick start (Windows)

From `elysia-native/__ctrl__/`:

```bat
elysia-native-ctrl.bat
```

Interactive prompt, or one-shot:

```bat
elysia-native-ctrl.bat setup-local
elysia-native-ctrl.bat dev run all
elysia-native-ctrl.bat test all
elysia-native-ctrl.bat list
elysia-native-ctrl.bat connect
```

Linux/mac:

```bash
chmod +x elysia-native-ctrl.sh
./elysia-native-ctrl.sh status
```

## Command map

| Area | Commands |
|------|----------|
| Local tooling | `setup-local [--force]` |
| Dev stack | `dev run\|stop\|down\|purge\|reset {infra,apps,all}` · `--slim` for lightweight runtime |
| App scaffold | `app create <name>` |
| Tests | `test {all,backend,frontend,contract}` |
| Local prod smoke | `prod start\|stop\|reset\|backup-acme\|…` |
| SSH / VM | `setup`, `pubkey`, `clone`, `env`, `start`, `stop`, `update`, `reset`, `backup-acme`, `connect`, … |

On-VM bash/bat scripts (what SSH `start`/`stop` invoke) live in [`remote/`](remote/README.md).

## Layout

| Path | Role |
|------|------|
| `servers.json` | Single VM entry (`elysia-native`) |
| `safe/` | PEM, address, prod `.env` |
| `static/gpg` | Docker Ubuntu GPG (Iran bootstrap) |
| `remote/` | On-VM / local-prod compose scripts |
| `elysia-native-ctrl.bat` / `.sh` | CLI entry |

## Typical first deploy (SSH)

```bat
elysia-native-ctrl.bat setup
elysia-native-ctrl.bat pubkey
REM add VM pubkey to GitHub
elysia-native-ctrl.bat clone
elysia-native-ctrl.bat env
elysia-native-ctrl.bat start
```

Day-2:

```bat
elysia-native-ctrl.bat update
elysia-native-ctrl.bat status
elysia-native-ctrl.bat backup-acme
```

## Local dev (Docker Desktop / host apps)

```bat
elysia-native-ctrl.bat setup-local
elysia-native-ctrl.bat dev run all
elysia-native-ctrl.bat dev stop all
elysia-native-ctrl.bat dev down all
elysia-native-ctrl.bat dev purge infra
elysia-native-ctrl.bat dev reset all
```

| Action | Infra (compose.dev.yml) | Apps (host) |
|--------|-------------------------|-------------|
| `run` / `start` | `up -d` db, redis (full), proxy, adminer | Bun API :8000 (runs Drizzle migrations), Redis queue worker (full), installed web kit :5000 |
| `stop` | `compose stop` — containers kept | kill host processes |
| `down` | `compose down` — volumes kept | kill host processes |
| `purge` | `compose down -v` — wipe data, stay down | kill host processes |
| `reset` | wipe then `run` | stop then run |

| Target | Notes |
|--------|-------|
| `infra` | Docker only. Drizzle migrations run when the API starts |
| `apps` | host processes (needs infra already up) |
| `all` | run: infra→apps · stop/down/purge/reset: apps→infra |

Opens browser tabs for Adminer / Traefik / dashboard / API docs after a successful run.

**Runtime profiles:** `dev run all` (full — includes Redis + worker) · `dev run all --slim` (no Redis/worker). See [docs/runtime-profiles.md](../docs/runtime-profiles.md).

## Tests

```bat
elysia-native-ctrl.bat test all
elysia-native-ctrl.bat test backend
elysia-native-ctrl.bat test frontend
elysia-native-ctrl.bat test contract --base http://localhost:8000
```

`test backend` runs `bun test ../tests/backend` in `backend/` on in-memory Postgres (PGlite), so it needs no services. `test contract` runs `tests/contract/contract_test.py` against a running API (`ENVIRONMENT=local`, Postgres, Redis and the worker up).

## Local production smoke

```bat
elysia-native-ctrl.bat prod start
elysia-native-ctrl.bat prod stop
elysia-native-ctrl.bat prod reset
elysia-native-ctrl.bat prod backup-acme
```

Same scripts SSH uses under `remote/`. Prefer SSH `start`/`stop` when operating the real VM from your laptop.

## Setup (ctrl tool itself)

```bat
python -m venv .venv
.venv\Scripts\pip install -r requirements.txt
```

`setup-local` also runs `npm install` for the frontend workspace.

On first `setup-local` / `dev run all`, the ctrl entry installs system **Python 3.10+** (via winget / Homebrew / apt) if missing, then `_setup_local` installs **Node.js LTS + npm** the same way before creating the project `.venv` and running `npm install`.

Iran VMs (`iran_setup: true`) keep provider DNS, rewrite apt to Arvan `apt_mirror`, and use Arvan Docker `registry_mirror`. `clone` routes GitHub SSH via `ssh.github.com:443`.

## Logs

```bat
REM Production VM (SSH)
elysia-native-ctrl.bat logs api
elysia-native-ctrl.bat logs db --no-follow

REM Local development
elysia-native-ctrl.bat dev logs api
elysia-native-ctrl.bat dev logs db

REM Local compose.yml smoke
elysia-native-ctrl.bat prod logs api
```

## Flatten / restore-flat

```bat
elysia-native-ctrl.bat flatten --yes
elysia-native-ctrl.bat restore-flat
elysia-native-ctrl.bat restore-flat --server <id> --yes
```

