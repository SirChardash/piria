module.exports = {
    i18n: {
        locales: ['en', 'sr'],
        defaultLocale: 'en',
    },
    env: {
        MUSEUM_APP_ENDPOINT: process.env.MUSEUM_APP_ENDPOINT,
        KEYCLOAK_ENDPOINT: process.env.KEYCLOAK_ENDPOINT
    }
}