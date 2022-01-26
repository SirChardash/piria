import {Avatar, Card, CardHeader, Grid} from "@mui/material";
import useSWR from "swr";

export default function WeatherPanel({countryCode}) {
    const expectedCardCount = 3
    const {
        data,
        error
    } = useSWR('http://localhost:8081/weather?language=hr&countryCode=' + countryCode, url => fetch(url).then(r => r.json()))

    if (error) return <Grid py={5}/>
    if (!data) return (
        <Grid container justifyContent={'space-evenly'} py={5}>
            {Array(expectedCardCount).map(() => <EmptyWeatherCard py={2}/>)}
        </Grid>
    )

    return (
        <Grid container justifyContent={'space-evenly'} py={5}>
            {data.data.map(data => <Grid item xs={12} sm={3} py={2} key={data.cityName}><WeatherCard data={data}/></Grid>)}
        </Grid>
    )
}

function EmptyWeatherCard() {
    return (<Card>
        <CardHeader/>
    </Card>)
}

function WeatherCard({data}) {
    return (
        <Card>
            <CardHeader
                avatar={
                    <Avatar src={data.iconUrl}/>
                }
                title={data.cityName + ', ' + data.description}
                subheader={data.temperature + ', feels like ' + data.feelsLikeTemperature}
            />
        </Card>)
}