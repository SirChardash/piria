import {useKeycloak} from "@react-keycloak/ssr";
import Layout from "../components/layout";
import {useEffect} from "react";
import FullScreenAlert from "../components/fullScreenAlert";

export default function SignOut() {
    const {keycloak, initialized} = useKeycloak()

    useEffect(() => {
        if (initialized && keycloak.authenticated) {
            keycloak?.logout()
        }
    }, [initialized, keycloak.authenticated])

    const content = initialized && !keycloak.authenticated
        ? <FullScreenAlert severity={'success'} title={'Sign out'}>You have successfully signed out.</FullScreenAlert>
        : <FullScreenAlert severity={'info'} title={'Sign out'}>Hold on. We're signing you out.</FullScreenAlert>

    return (
        <Layout>
            {content}
        </Layout>
    )
}