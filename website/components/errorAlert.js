import {useRouter} from "next/router";
import FullScreenAlert from "./fullScreenAlert";
import fullL10n from "../l10n";

export default function ErrorAlert({status}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].errorAlert

    return <FullScreenAlert severity={'error'} title={l10n.title}>{l10n.status[status]}</FullScreenAlert>
}