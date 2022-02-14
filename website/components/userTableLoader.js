import {useKeycloak} from "@react-keycloak/ssr";
import useSWR from "swr";
import fetcher from "../lib/fetch";
import {Grid, LinearProgress} from "@mui/material";
import Typography from "@mui/material/Typography";
import UserTable from "./userTable";

export default function UserTableLoader({endpoint}) {
    const {keycloak} = useKeycloak()

    const {data, error} = useSWR(endpoint, url => fetcher(url, keycloak.token))

    if (error) return <Grid justifyContent={'center'} py={18}>
        <Typography variant={'h5'} color={'text.secondary'}>failed to load</Typography>
    </Grid>

    if (!data) return (
        <Grid item xs={10} sm={6} justifyContent={'center'} py={20}>
            <LinearProgress/>
        </Grid>

    )

    return (
        <>
            <Grid container justifyContent={'right'}>
                <Typography color={'text.secondary'} sx={{mb: 1.5, fontSize: 14}}>
                    Total users: {data.length}
                </Typography>
            </Grid>
            <UserTable data={data}/>
        </>
    )
}