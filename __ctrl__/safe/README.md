# safe/ — keys, addresses, prod env (local only)

| Pattern | Purpose |
|---------|---------|
| `*-privatekey.pem` | SSH private key |
| `*-address.txt` | VM IP / hostname (first line) |
| `*-env.env` | Production secrets → uploaded as `~/projects/elysia-native/.env` |

| Files | Server id |
|-------|-----------|
| `ar-elysia-native-bamdad-*` | `elysia-native` |

Copy the `*.example` stubs, drop the `.example` suffix, and fill real values.

`*.pem`, `*.env`, `*-address.txt` are gitignored.

Upload env to VM:

```bat
elysia-native-ctrl.bat env
```

That copies `safe/ar-elysia-native-bamdad-env.env` → `~/projects/elysia-native/.env`.
