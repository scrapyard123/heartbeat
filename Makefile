VERSION=0.1.0

PHONY=clean build install ni-tracing
default: build

target/uberjar/heartbeat-$(VERSION)-standalone.jar:
	lein uberjar

# https://github.com/clj-easy/graalvm-clojure/blob/master/doc/clojure-graalvm-native-binary.md
target/uberjar/heartbeat: target/uberjar/heartbeat-$(VERSION)-standalone.jar
	native-image \
		--report-unsupported-elements-at-runtime \
		--initialize-at-build-time \
		-jar target/uberjar/heartbeat-$(VERSION)-standalone.jar \
		-H:Name=target/uberjar/heartbeat

clean:
	lein clean

build: target/uberjar/heartbeat

install: build
	mkdir -p "$${HOME}/.local/bin"
	cp target/uberjar/heartbeat "$${HOME}/.local/bin"

	mkdir -p "$${HOME}/.config/systemd/user"
	cp heartbeat.timer "$${HOME}/.config/systemd/user"
	cp heartbeat.service "$${HOME}/.config/systemd/user"

# https://www.graalvm.org/latest/reference-manual/native-image/guides/configure-with-tracing-agent/
# Needs manual editing afterwards
ni-tracing: target/uberjar/heartbeat-$(VERSION)-standalone.jar
	java -agentlib:native-image-agent=config-output-dir=resources/META-INF/native-image -jar target/uberjar/heartbeat-$(VERSION)-standalone.jar
