.PHONY: build run

default: build

build:
	./mvnw clean verify

run:
	export $$(cat .env | xargs) && ./mvnw spring-boot:run
