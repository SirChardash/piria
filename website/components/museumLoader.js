import useSWR from "swr";
import MuseumPanel from "./museumPanel";
import {Grid, LinearProgress} from "@mui/material";
import Typography from "@mui/material/Typography";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {useKeycloak} from "@react-keycloak/ssr";
import fetcher from "../lib/fetch";

export default function MuseumLoader({endpoint}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumLoader
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

    if (data.museums.length === 0) {
        return (
            <Grid justifyContent={'center'} py={18}>
                <Typography variant={'h5'} color={'text.secondary'}>{l10n.noResults}</Typography>
            </Grid>
        )
    }

    return <MuseumPanel museums={data.museums}/>
}