import {CircularProgress, Grid} from "@mui/material";
import Layout from "./layout";

export default function Loading({withLayout, authenticated}) {
    const loading = <Grid container justifyContent={'center'}>
        <Grid item py={20}>
            <CircularProgress/>
        </Grid>
    </Grid>

    return withLayout
        ? <Layout authenticated={authenticated}>
            {loading}
        </Layout>
        : loading
}