import Layout from "../../components/layout";
import MessageLoader from "../../components/messageLoader";
import {Grid} from "@mui/material";
import endpoints from "../../endpoints";

export default function Messages() {
    return (
        <Layout admin>
            <Grid container py={8} maxWidth>
                <Grid item xs={12} key={'messages'}>
                    <MessageLoader endpoint={endpoints.museumApp + '/admin/messages'}/>
                </Grid>
            </Grid>
        </Layout>
    )
}