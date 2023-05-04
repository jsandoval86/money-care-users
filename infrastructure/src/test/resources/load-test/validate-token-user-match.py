from locust import HttpUser, task
import time

class verify_token(HttpUser):

    token1 = "eyJhbGciOiJub25lIn0.eyJpZF91c2VyIjoiZjNlMjc5Y2YtNDE0Yy00MDcxLWFiYmYtOWM5MWQ5ZWNiNTcyIiwiaWRfdGVuYW50IjoiMDYzZWY5YzYtZWQ4Ny00MmZlLWJkMzYtZTg0ZWNiNWRlYmY0Iiwicm9sZSI6InN1cGVyLWFkbWluIiwiY29wcm9waWVkYWRlcyI6WyIzZDJiNWMyNS0yODRhLTQxNzUtOGRhYy1mODRkMWQ0MTA2NWQiLCJhNjM1MmYwZC1lYmJmLTQ5NjEtOTBjNy1lNjg3YTY1ZGZmMjkiXSwiYWNjZXNzX3Rva2VuIjoiZDcyZmQyYWEtNmYzMy00Mzg0LWJjOWItM2NiYTUzMjJlZTg0ZTZlZTI3MjUtOTRmOS00ZjU5LTk4MzYtM2RlYjUxY2FhNDU0NGZmZDk4NDgtZTlhMy00ZWMyLWJkYzItNDRlYzY2Y2VkZGI1MjNhMzY2MTUtODYzOS00ZTdkLWE2MDMtMzNmYzUwYWJmZGI2In0."
    token2 = "eyJhbGciOiJub25lIn0.eyJpZF91c2VyIjoiYjNmYzEyNDktMmU3My00YWRmLTkyYmEtYjRiYjhhZjQxZTg3IiwiaWRfdGVuYW50IjoiMDE0N2E3NzktZTdkMi00MzNhLTlhNmUtNDY1Mjc0MGE0N2I3Iiwicm9sZSI6InN1cGVyLWFkbWluIiwiY29wcm9waWVkYWRlcyI6WyI1YjU1OTc1NC1hMjM3LTQwMDktODk1ZS03NjQwZDhkNGEyYmMiLCJlOTE3ZjMzZC01OGQ5LTQxODQtYTEyNy1hYzMxMjVlMmUwZGUiXSwiYWNjZXNzX3Rva2VuIjoiOWQyZmJkNGEtMjNjZi00YzNiLWJhNjgtMGYxOThjNmI1MDhkYjVjZmYwYTYtMDcxYS00NGZjLWFkODMtZjAyYWRlODE3NzFhYzUyMjFmNDAtYWNjYS00NTljLWFiN2QtNGI0YzRjYmRhYjUzMzFiMjk2ZGUtMzIwOC00YTEwLWJkOGEtNjQ5ZjZhYTY1OTFkIn0."

    user_token_1 = "f3e279cf-414c-4071-abbf-9c91d9ecb572"
    user_token_2 = "b3fc1249-2e73-4adf-92ba-b4bb8af41e87"

    @task
    def user1(self):
        with self.client.get("/api/users/v1/users",
                             headers = { 'Authorization': self.token1 },
                             catch_response = True) as response:
            if response.status_code == 200:
                if response.json().get("user_id") == self.user_token_1:
                    response.success()
                else:
                    response.failure(f'error user is not correct for token')
            else:
                response.failure(f'error with status code = {response.status_code}')

        time.sleep(0.5)

    @task
    def user1(self):
        with self.client.get("/api/users/v1/users",
                             headers = { 'Authorization': self.token2 },
                             catch_response = True) as response:

            if response.status_code == 200:
                if response.json().get("user_id") == self.user_token_2:
                    response.success()
                else:
                    response.failure(f'error user is not correct for token')
            else:
                response.failure(f'error with status code = {response.status_code}')

        time.sleep(0.5)
