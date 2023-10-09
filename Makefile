up:
	./gradlew build
	docker-compose up -d --build

logs:
	docker-compose logs -f

stop:
	docker-compose stop

down:
	docker-compose down

test:
	find ./ -name build | xargs rm -rf && ktlint
	./gradlew test

migrate:
	cat .migrations/create_tasks_table.sql | docker compose exec -T mysql /usr/bin/mysql -uroot -proot default
