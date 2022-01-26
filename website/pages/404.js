import Layout from "../components/layout";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import FullScreenAlert from "../components/fullScreenAlert";

export default function Custom404() {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].page404

    return (
        <Layout>
            <FullScreenAlert severity={'error'} title={l10n.title}>{l10n.text}</FullScreenAlert>
        </Layout>
    )
}
