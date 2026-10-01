#!/usr/bin/env bash
# start backend with the H2 dev database (no MySQL needed)
# uses a JDK extracted under ~/.local/jdk when java is not installed
set -e
if ! command -v java >/dev/null; then
  JDK=$(ls -d "$HOME"/.local/jdk/jdk-* 2>/dev/null | head -1)
  [ -z "$JDK" ] && { echo "No JDK found. Extract one into ~/.local/jdk"; exit 1; }
  export JAVA_HOME="$JDK" PATH="$JDK/bin:$PATH"
fi
cd "$(dirname "$0")"
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
