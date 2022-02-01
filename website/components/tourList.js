import {Card, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import PrettyDate from "./prettyDate";
import fullL10n from "../l10n";
import {useRouter} from "next/router";
import styles from '../styles/tourList.module.css'
import {parseISO} from "date-fns";

export default function TourList({list, showStatus}) {
    return (
        <>
            {list.map(tour => <TourItem key={tour.id} data={tour} showStatus={showStatus}/>)}
        </>
    )
}

function TourItem({data, showStatus}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].tours

    const status = showStatus
        ? <strong>{getStatus(data.startTime, data.endTime)}{' '}</strong>
        : ''

    console.log(status)
    return (
        <Card sx={{minWidth: 275}} variant={'outlined'} className={styles.card}>
            <Grid container py={2} px={2}>
                <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                    {status}
                    {l10n.from}{' '}
                    <PrettyDate dateString={data.startTime} timeOnly/>
                    {' '}{l10n.to}{' '}
                    <PrettyDate dateString={data.endTime} timeOnly/>
                    ,{' '}
                    <PrettyDate dateString={data.startTime} dateOnly/>
                </Typography>
                <Grid container>
                    <Typography variant="h6" component="div">
                        {data.title}
                    </Typography>
                </Grid>
                <Grid>
                    <Typography>
                        {data.description}
                    </Typography>
                </Grid>
            </Grid>
        </Card>
    )

    function getStatus(startTime, endTime) {
        const start = parseISO(startTime).getTime()
        const end = parseISO(endTime).getTime()
        const now = new Date().getTime()

        return now < start
            ? ''
            : now < end
                ? l10n.inProgress
                : l10n.finished
    }
}

