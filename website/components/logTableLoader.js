import {useKeycloak} from "@react-keycloak/ssr";
import useSWR from "swr";
import fetcher from "../lib/fetch";
import {Grid, LinearProgress} from "@mui/material";
import Typography from "@mui/material/Typography";
import LogTable from "./logTable";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function LogTableLoader({endpoint}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].stats

    const {keycloak} = useKeycloak()

    const {data, error} = useSWR(endpoint, url => fetcher(url, keycloak.token))

    if (error) return <Grid justifyContent={'center'} py={18}>
        <Typography variant={'h5'} color={'text.secondary'}>{l10n.failedToLoad}</Typography>
    </Grid>

    if (!data) return (
        <Grid item xs={10} sm={6} justifyContent={'center'} py={20}>
            <LinearProgress/>
        </Grid>

    )

    return <LogTable data={data}/>
}