# Task Description
## Selenium Assignment

For the full assignment description, task list, and deadlines, see the course materials.

Fill in `points.yml` to track your progress. The CI will calculate your grade on every push.

# Usage options
## Config file
To configure the test runner see:
`tests/selenium/src/main/resources/config.properties`

## Containerized test environment
By default the test suite will connect to `https://parabank.parasoft.com`. To run it use the command:  
1. `docker compose --profile test-public up -d`
2. `docker exec -it -u selenium docker-sandbox-ubuntu bash -c "cd tests && gradle clean test"`
3. `docker compose --profile test-public down -v`

## Local hosted service
If the public service is down or being under high load, you can use the preconfigured localized environment.
To run a docker image with the parabank web app locally, use the `--profile test-local` instead of the `test-public` profile in docker. This will pull the containerized version of the parabank web application and run it on localhost. To connect to it change the `env` variable to `local` in the config file.

## Screenshot on failure
The test runner is preconfigured to take screenshut when a test failure occurs. Use the `directory.screenshots` config variable to assign the location to save these images.

## UI vs headless mode
The runner is preconfigured to run without observable UI and will display test results in the terminal. To see the full execution, connect to `localhost:7900` from a browser, change `browser.headless` to `false` in the config, and you can observe the test flow visually in the browser window.
