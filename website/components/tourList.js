import {Card, CardActions, CardContent, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import PrettyDate from "./prettyDate";
import fullL10n from "../l10n";
import {useRouter} from "next/router";
import {parseISO} from "date-fns";
import {logEvent} from "../lib/tracking";
import getUserId from "../lib/userId";
import {useKeycloak} from "@react-keycloak/ssr";
import Button from "@mui/material/Button";
import Link from "next/link";
import * as React from "react";
import styles from '../styles/header.module.css';

export default function TourList({list, showStatus, canBook, context, canVisit}) {
    const {keycloak, initialized} = useKeycloak()
    const {locale} = useRouter()
    const l10n = fullL10n[locale].tours

    return (
        <>
            {list.map(tour => <TourItem key={tour.id} data={tour}/>)}
        </>
    )

    function TourItem({data}) {
        const status = showStatus
            ? <strong>{getStatus(data.startTime, data.endTime)}{' '}</strong>
            : ''

        const paymentNumber = generatePaymentNumber()
        const buttons =
            <CardActions className={styles.cardActions}>
                {
                    canBook
                        ? <form action={'http://localhost:3005/' + locale} onSubmit={buyTicket}>
                            <input value={paymentNumber} name={'paymentNumber'} hidden/>
                            <input value={data.ticketPrice} name={'amount'} hidden/>
                            <input value={'3205732519283123'} name={'receiver'} hidden/>
                            <input value={'http://localhost:3000/purchase/' + data.id + '/' + paymentNumber}
                                   name={'callbackUrl'} hidden/>
                            <input value={'attendance of ' + keycloak.idTokenParsed.sub + ' to tour ' + data.id}
                                   name={'purpose'} hidden/>
                            <Button size={'small'} type={'submit'}>{l10n.buyTicket}</Button>
                        </form>
                        : ''
                }

                {
                    canVisit
                        ? <Link href={'/visit/' + data.id}>
                            <Button size={'small'}>
                                {l10n.visit}
                            </Button>
                        </Link>
                        : ''
                }
            </CardActions>

        return (
            <Card sx={{minWidth: 275}} variant={'outlined'} className={styles.card}>
                <CardContent>
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
                </CardContent>
                {buttons}
            </Card>
        )

        function buyTicket() {
            logEvent(
                initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
                context,
                'tours',
                'buy ticket',
                data.id
            )
        }

        function generatePaymentNumber() {
            return ''
                + new Date().getTime() % 1_000_000
                + keycloak.idTokenParsed.iat % 1_000_000
                + Math.floor(1000 + Math.random() * 8999)
        }

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
}

