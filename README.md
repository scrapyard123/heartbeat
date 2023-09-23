# heartbeat
Utility to collect basic metrics and write them to systemd logs.

## Installation
1. Download and install GraalVM and Native Image (see [Documentation](https://www.graalvm.org/22.0/reference-manual/native-image/)).
2. Configure `JAVA_HOME`, `PATH` to refer to Graal installation.
3. Run `make install` - it will compile and install `heartbeat` in your home directory along with systemd unit files.
4. Run `systemctl --user enable heartbeat.timer`  and `systemctl --user start heartbeat.timer`  to enable timer for `heartbeat`.
5. You can use `journalctl -t heartbeat --output cat` to extract metrics from logs.
