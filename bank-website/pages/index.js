import Head from 'next/head'
import {
    Alert,
    AlertTitle,
    Button,
    CircularProgress,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    TextField,
    Typography
} from "@mui/material";
import styles from '../styles/index.module.css'
import Image from 'next/image'
import {useState} from "react";
import fullL10n from "../l10n";
import {useRouter} from "next/router";
import axios from "axios";

export default function Home() {
    const [paymentStep, setPaymentStep] = useState('pay') // pay, awaiting, result
    const [statusCode, setStatusCode] = useState('OK')


    const router = useRouter()
    const {locale} = router
    const l10n = fullL10n[locale].paymentPage

    const {paymentNumber, amount, receiver, successUrl} = router.query

    const content = (!paymentNumber || !amount || !receiver || !successUrl)
        ? <BadRequest/>
        : paymentStep === 'pay'
            ? <PaymentForm/>
            : paymentStep === 'awaiting'
                ? <Awaiting/>
                : <Result/>

    return (
        <div>
            <Head>
                <title>ABB</title>
            </Head>
            <main>
                <Grid container justifyContent={'center'}>
                    <Grid item xs={10} sm={6} className={styles.panel}>
                        <Grid container justifyContent={'center'}>
                            <Image src={'/logo.png'} width={250} height={250}/>
                        </Grid>
                        <Grid container justifyContent={'center'}>
                            <Image src={'/logo-name.png'} width={250} height={125}/>
                        </Grid>
                        <Grid container justifyContent={'center'} py={5}>
                            {content}
                        </Grid>
                    </Grid>
                </Grid>
            </main>
        </div>
    )

    function BadRequest() {
        return (
            <Grid item xs={10} sm={8} md={6}>
                <Alert severity="error">
                    <AlertTitle>{l10n.errorTitle}</AlertTitle>
                    {l10n.invalidPaymentRequest}
                </Alert>
            </Grid>
        )
    }

    function PaymentForm() {
        return (
            <Grid item xs={10} sm={8} md={6}>
                <form onSubmit={submit}>
                    <Alert severity="info">
                        {l10n.infoMessage.youAreRequestedToPay}{' '}
                        <strong>{amount}</strong>{' '}
                        {l10n.infoMessage.to}{' '}<strong>{receiver}</strong>.{' '}
                        {l10n.infoMessage.referToTheNumber}{' '}<strong>[{paymentNumber}]</strong>.
                    </Alert>
                    <TextField className={styles.input}
                               id="standard-basic"
                               label={l10n.firstName}
                               variant="standard"
                               name={'firstName'}/>
                    <TextField className={styles.input}
                               id="standard-basic"
                               label={l10n.lastName}
                               variant="standard"
                               name={'lastName'}/>
                    <TextField className={styles.input}
                               id="standard-basic"
                               label={l10n.cardNumber}
                               variant="standard"
                               name={'cardNumber'}/>
                    <Grid item xs={12} className={styles.input}>
                        <InputLabel id={'cardTypeLabel'}>{l10n.cardType}</InputLabel>
                        <CardTypeSelect/>
                    </Grid>
                    <Grid container justifyContent={'space-between'}>
                        <Grid item xs={12} lg={5}>
                            <TextField className={styles.input}
                                       id="standard-basic"
                                       label={l10n.expirationDate}
                                       variant="standard"
                                       name={'expirationDate'}/>
                        </Grid>
                        <Grid item xs={12} md={5}>
                            <TextField className={styles.input}
                                       id="standard-basic"
                                       label={l10n.securityCode}
                                       variant="standard"
                                       name={'securityCode'}/>
                        </Grid>
                    </Grid>
                    <Button variant={'contained'} className={styles.submit} type={'submit'}>{l10n.submit}</Button>
                </form>
            </Grid>
        )
    }

    function submit(event) {
        event.preventDefault()
        const config = {headers: {'Content-Type': 'application/json'}}
        setPaymentStep('awaiting')
        const blob = new Blob([JSON.stringify({
            "amount": amount,
            "referenceNumber": paymentNumber,
            "payerCard": {
                "cardNumber": event.target.cardNumber.value,
                "type": event.target.cardType.value,
                "firstName": event.target.firstName.value,
                "lastName": event.target.lastName.value,
                "expirationDate": event.target.expirationDate.value,
                "securityCode": event.target.securityCode.value
            },
            "inFavorOfAccountNumber": receiver
        })], {
            type: "application/json"
        });
        axios.post('http://localhost:8082/pay', blob, config)
            .then(processGoodResponse)
            .catch(processBadResponse)
    }

    async function processGoodResponse(response) {
        setStatusCode(response.data.status)
        setPaymentStep('result')
    }

    function processBadResponse(error) {
        setStatusCode(error.response.data.status)
        setPaymentStep('result')
    }

    function Awaiting() {
        return (
            <Grid container justifyContent={'center'} py={12}>
                <Grid item>
                    <CircularProgress/>
                </Grid>
            </Grid>
        )
    }

    function Result() {
        const severity = statusCode === 'OK' ? 'success' : 'error'
        const title = statusCode === 'OK' ? l10n.transactionSuccess : l10n.transactionFail
        const action = statusCode === 'OK'
            ? <Typography>{l10n.redirecting}{' '}<a href={successUrl}>{l10n.clickHere}</a>.</Typography>
            : <Button color={'error'} size={'small'} onClick={() => setPaymentStep('pay')}>{l10n.back}</Button>

        return (
            <Grid item xs={10} sm={8} md={6}>
                <Alert severity={severity}>
                    <AlertTitle>{title}</AlertTitle>
                    {l10n.paymentStatusMessage[statusCode]}
                </Alert>
                <Grid container justifyContent={'center'} py={2}>
                    {action}
                </Grid>
            </Grid>
        )
    }

}

function CardTypeSelect() {
    const [cardType, setCardType] = useState('VISA')

    return (
        <Select fullWidth label={'Type'}
                labelId={'cardTypeLabel'}
                variant={'standard'}
                onChange={event => setCardType(event.target.value)}
                value={cardType}
                name={'cardType'}>
            <MenuItem value={'VISA'}>VISA</MenuItem>
            <MenuItem value={'MASTERCARD'}>MASTERCARD</MenuItem>
            <MenuItem value={'AMERICAN EXPRESS'}>AMERICAN EXPRESS</MenuItem>
        </Select>
    )
}