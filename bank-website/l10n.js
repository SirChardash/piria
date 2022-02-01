const fullL10n = {
    en: {
        paymentPage: {
            firstName: 'First Name',
            lastName: 'Last Name',
            cardNumber: 'Card Number',
            cardType: 'Card Type',
            expirationDate: 'Expiration Date',
            securityCode: 'Security Code',
            submit: 'Submit',
            back: 'Back to Payment',
            redirecting: 'Redirecting you shortly. If nothing happens,',
            clickHere: 'click here',
            infoMessage: {
                youAreRequestedToPay: 'You\'ve been requested to pay',
                to: 'to',
                referToTheNumber: 'In case of disagreement, refer to the payment number'
            },
            errorTitle: 'An error occurred!',
            invalidPaymentRequest: 'The link you followed leads to an incomplete or invalid payment request.',
            transactionSuccess: 'Transaction complete!',
            transactionFail: 'Could not complete transaction.',
            paymentStatusMessage: {
                'OK': 'Transaction is successful.',
                'NOT_ENOUGH_MONEY': 'You do not have enough money to complete the transaction.',
                'BAD_CARD_INFO': 'Credit card info you provided is not correct, or your card has expired.',
                'BAD_IN_FAVOR_INFO': 'The receiver doesn\'t exist.'
            }
        }
    },
    sr: {
        paymentPage: {
            firstName: 'Ime',
            lastName: 'Prezime',
            cardNumber: 'Broj na kartici',
            cardType: 'Vrsta kartice',
            expirationDate: 'Datum isteka',
            securityCode: 'Sigurnosni kod',
            submit: 'Plati',
            back: 'Nazad na uplatu',
            redirecting: 'Preusmjeravamo vas nazad na kupovinu. U slučaju da se ništa ne dogodi,',
            clickHere: 'kliknite ovdje',
            infoMessage: {
                youAreRequestedToPay: 'Ovim putem uplaćujete svotu ',
                to: 'na račun',
                referToTheNumber: 'U slučaju nesuglasice, pozovite se na broj uplate '
            },
            errorTitle: 'Došlo je do greške!',
            invalidPaymentRequest: 'Proces koji ste inicirali vodi do nekompletnog ili nevalidnog zahtjeva za uplatu.',
            transactionSuccess: 'Uplata uspješna!',
            transactionFail: 'Uplata se ne može izvršiti.',
            paymentStatusMessage: {
                'OK': 'Uplata je izvršena.',
                'NOT_ENOUGH_MONEY': 'Nemate dovoljno novca da izvršite uplatu.',
                'BAD_CARD_INFO': 'Podaci koje ste unijeli nisu korektni ili je vaša kartica istekla.',
                'BAD_IN_FAVOR_INFO': 'Pokušali ste uplatiti nepostojećem klijentu.'
            }
        }
    }
}

export default fullL10n