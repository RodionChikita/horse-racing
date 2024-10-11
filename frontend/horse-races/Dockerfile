# Используем Node.js для сборки
FROM node:18 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Используем Nginx для сервировки фронтенда
FROM nginx:alpine
COPY --from=build /app/dist/horse-races /usr/share/nginx/html
EXPOSE 80
