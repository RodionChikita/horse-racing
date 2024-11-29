export const environment = {
  production: false,
  apiUrl: '/api',
  keycloak: {
    url: 'http://localhost:8080/auth', // Адрес вашего Keycloak сервера
    realm: 'horse-races',              // Ваш Realm в Keycloak
    clientId: 'frontend',              // ID вашего клиента в Keycloak
  },
};
