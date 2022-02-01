import useSWR from "swr";
import {CircularProgress, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {useKeycloak} from "@react-keycloak/ssr";
import fetcher from "../lib/fetch";
import TourList from "./tourList";

export default function TourLoader({endpoint, noResultText, showStatus}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].tours
    const {keycloak} = useKeycloak()

    const {data, error} = useSWR(endpoint, url => fetcher(url, keycloak.token))

    if (error) return <Grid justifyContent={'center'}>
        <Typography color={'text.secondary'}>{l10n.failedToLoad}</Typography>
    </Grid>

    if (!data) return (
        <Grid item xs={10} sm={6} justifyContent={'center'} py={20}>
            <CircularProgress/>
        </Grid>

    )

    if (data.tours.length === 0) {
        return (
            <Grid justifyContent={'center'}>
                <Typography color={'text.secondary'}>{noResultText}</Typography>
            </Grid>
        )
    }

    return <TourList list={data.tours} showStatus={showStatus}/>
}