import useSWR from "swr";
import MuseumPanel from "./museumPanel";
import {Grid, LinearProgress} from "@mui/material";
import Typography from "@mui/material/Typography";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function MuseumLoader({endpoint}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumLoader

    const {data, error} = useSWR(endpoint, url => fetch(url).then(r => r.json()))

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