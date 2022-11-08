import '../styles/global.css'
import cookie from 'cookie'
import * as React from 'react'
import {SSRCookies, SSRKeycloakProvider} from '@react-keycloak/ssr'
import endpoints from "../endpoints";

const keycloakCfg = {
    realm: 'bravesmart',
    url: endpoints.authService + "/auth",
    clientId: 'bravesmart-web'
}


export default function App({Component, pageProps, cookies}) {
    return (
        <SSRKeycloakProvider
            keycloakConfig={keycloakCfg}
            persistor={SSRCookies(cookies)}
            initOptions={{
                onload: 'check-sso',
                silentCheckSsoRedirectUrl: 'http://localhost:3000/silent-check-sso.html'
            }}>
            <Component {...pageProps} />
        </SSRKeycloakProvider>
    )
}

App.getInitialProps = async (context) => {
    return {
        cookies: parseCookies(context?.ctx?.req)
    }
}

function parseCookies(req) {
    if (!req || !req.headers) {
        return {}
    }
    return cookie.parse(req.headers.cookie || '')
}