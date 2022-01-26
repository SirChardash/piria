import useSWR from "swr";
import MuseumPanel from "./museumPanel";
import {Grid, LinearProgress} from "@mui/material";
import Typography from "@mui/material/Typography";

export default function MuseumLoader({endpoint}) {
    const {data, error} = useSWR(endpoint, url => fetch(url).then(r => r.json()))

    if (error) return <div>failed to load</div>
    if (!data) return (
        <Grid item xs={10} sm={6} justifyContent={'center'} py={20}>
            <LinearProgress/>
        </Grid>

    )
    if (data.museums.length === 0) {
        return (
            <Grid justifyContent={'center'} py={18}>
                <Typography variant={'h5'} color={'text.secondary'}>
                    No results.
                </Typography>
            </Grid>
        )
    }
    return <MuseumPanel museums={data.museums}/>
}