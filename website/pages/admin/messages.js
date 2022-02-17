import Layout from "../../components/layout";
import MessageLoader from "../../components/messageLoader";
import {Grid} from "@mui/material";

export default function Messages() {
    return (
        <Layout admin>
            <Grid container py={8} maxWidth>
                <Grid item xm={12} key={'messages'}>
                    <MessageLoader endpoint={'http://localhost:8081/admin/messages'}/>
                </Grid>
            </Grid>
        </Layout>
    )
}