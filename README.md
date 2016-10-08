# Iot-server

This is an application for receive requests from iot devices and send them to firebase cloud message.

## Configuration

* Postgres
```
export DATABASE_URL=postgres://{username}:{passoword}@{host}:5432/{database}
```
* Firebase Cloud Message
```
export FCM_URL=https://fcm.googleapis.com/fcm/send
export FCM_API_KEY={YOUR API KEY}
```
