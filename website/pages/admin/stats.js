import Layout from "../../components/layout";
import useSWR from "swr";
import fetcher from "../../lib/fetch";
import {useKeycloak} from "@react-keycloak/ssr";
import Loading from "../../components/loading";
import {ArgumentAxis, BarSeries, Chart, Title, ValueAxis} from '@devexpress/dx-react-chart-material-ui';
import {Paper} from "@mui/material";
import {Animation} from "@devexpress/dx-react-chart";

export default function Stats() {
    const {keycloak, initialized} = useKeycloak()

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

    if (isValidating) {
        return <Loading withLayout/>
    }

    console.log(data)

    return (
        <Layout admin>
            <Paper>
                <Chart data={data.userActivityPastDay} height={300}>
                    <ArgumentAxis/>
                    <ValueAxis/>
                    <BarSeries valueField="users" argumentField="period"/>
                    <Title text={'Active Users (' + data.activeUsers + ' right now)'}/>
                    <Animation/>
                </Chart>
            </Paper>
        </Layout>
    )
}