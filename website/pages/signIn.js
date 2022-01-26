import {useKeycloak} from "@react-keycloak/ssr";
import {useEffect} from "react";
import Layout from "../components/layout";
import FullScreenAlert from "../components/fullScreenAlert";

export default function SignIn() {
    const {keycloak, initialized} = useKeycloak()

    useEffect(() => {
        if (initialized && !keycloak.authenticated) {
            keycloak?.login()
        }
    }, [initialized, keycloak.authenticated])

    const content = initialized && keycloak.authenticated
        ? <FullScreenAlert severity={'success'} title={'Welcome'}>
            Welcome to BraveHeart. You can now browse museums and apply for virtual tours.
        </FullScreenAlert>
        : <FullScreenAlert severity={'info'} title={'Sign in'}>Signing you in.</FullScreenAlert>
    return (
        <Layout>
            {content}
        </Layout>
    )
}