import {useKeycloak} from "@react-keycloak/ssr";
import {useEffect} from "react";
import Layout from "../components/layout";
import FullScreenAlert from "../components/fullScreenAlert";
import fullL10n from "../l10n";
import {useRouter} from "next/router";

export default function SignIn() {
    const {keycloak, initialized} = useKeycloak()

    const {locale} = useRouter()
    const l10n = fullL10n[locale].signIn

    useEffect(() => {
        if (initialized && !keycloak.authenticated) {
            keycloak?.login()
        }
    }, [initialized, keycloak.authenticated])

    const content = initialized && keycloak.authenticated
        ? <FullScreenAlert severity={'success'} title={l10n.success.title}>{l10n.success.text}</FullScreenAlert>
        : <FullScreenAlert severity={'info'} title={l10n.progress.title}>{l10n.progress.text}</FullScreenAlert>
    return (
        <Layout>
            {content}
        </Layout>
    )
}