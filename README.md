vijos-bukkit-auth
=================

vijos-bukkit-auth is a CraftBukkit plugin originated from [MineLoginPlugin](https://gitcafe.com/MineLogin/MineLoginPlugin).

# Features:

- HTTP(s)-API-based authorization
- Customize authorization URI

# Configuration

```
Login:
  AtSpawn: true		# teleport to spawnpoint
  Chat: false		# allow chat when user isn't login
API:
  StatusURI: https://localhost/status	# query user existance
  LoginURI: https://localhost/login		# check password
```

# API

Your HTTP-API should return just one line with an integer.

## StatusAPI

- `0`: exists
- `1`: banned
- `2`: user not found

### Example:

```php
<?php

if (!isset($_POST['username']))
{
    echo '2';
    exit();
}

$result = \user\is_exists($_POST['username']);

if ($result === true)
    echo '0';
else
    echo '2';

?>
```

## LoginAPI

- `0`: OK
- `1`: password or username mismatch

### Example:

```php
<?php

if (!isset($_POST['username']) || !isset($_POST['hash']))
{
    echo '1';
	exit();
}

$result = \user\login_with_md5($_POST['username'], $_POST['hash']);

if ($result === true)
	echo '0';
else
	echo '1';

?>
```

# Warning

The plugin **DOES NOT** verify server credentials (HTTPS)!

If you want it work, please modify `src/com/vijoslogin/lib/URIPost.java`.

# Licence

GPL v3

# Author

Breezewish
