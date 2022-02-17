import Layout from "../../components/layout";
import useSWR from "swr";
import fetcher from "../../lib/fetch";
import {useKeycloak} from "@react-keycloak/ssr";
import Loading from "../../components/loading";
import {ArgumentAxis, BarSeries, Chart, Title, ValueAxis} from '@devexpress/dx-react-chart-material-ui';
import {Grid, Paper} from "@mui/material";
import {Animation} from "@devexpress/dx-react-chart";
import LogTableLoader from "../../components/logTableLoader";
import {Download} from "@mui/icons-material";
import Button from "@mui/material/Button";
import axios from "axios";
import {useRouter} from "next/router";
import fullL10n from "../../l10n";

export default function Stats() {
    const {keycloak, initialized} = useKeycloak()

    const {locale} = useRouter()
    const l10n = fullL10n[locale].stats

    if (!initialized) {
        return <Loading withLayout/>
    }

    const {
        data,
        error,
        isValidating
    } = useSWR(
        'http://localhost:8081/admin/user/stats',
        url => fetcher(url, keycloak.token)
    )

    if (isValidating || error) {
        return <Loading withLayout/>
    }

    return (
        <Layout admin>
            <Paper>
                <Chart data={data.userActivityPastDay} height={300}>
                    <ArgumentAxis/>
                    <ValueAxis/>
                    <BarSeries valueField="users" argumentField="period"/>
                    <Title text={l10n.activeUsers + ' (' + data.activeUsers + ' ' + l10n.atTheMoment + ')'}/>
                    <Animation/>
                </Chart>
            </Paper>
            <Button onClick={downloadLogs}><Download/>{l10n.downloadLogs}</Button>
            <Grid container py={3}>
                <LogTableLoader endpoint={'http://localhost:8081/admin/user/logs'}/>
            </Grid>
        </Layout>
    )

    function downloadLogs() {
        axios.get(
            'http://localhost:8081/admin/user/logs/pdf',
            {headers: {authorization: 'Bearer ' + keycloak.token}, responseType: 'blob'}
        ).then(result => {
            const url = window.URL.createObjectURL(new Blob([result.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'logs.pdf');
            document.body.appendChild(link);
            link.click();
        }).catch(reason => {
        })
    }
}