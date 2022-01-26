import {Container, Grid, Link} from "@mui/material";
import Box from "@mui/material/Box";
import styles from '../styles/footer.module.css';
import Image from 'next/image'
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function Footer() {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].footer

    return (
        <Box className={styles.footer}>
            <Container maxWidth='lg'>
                <Grid container px={{xs: 3, sm: 10}} py={{xs: 3, sm: 5}}>
                    <Grid item xs={12} sm={4}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>{l10n.p1.title}</strong>
                            <p>{l10n.p1.text}</p>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>{l10n.p2.title}</strong>
                            <p>{l10n.p2.text}</p>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>{l10n.p3.title}</strong>
                            <ul>
                                <li>
                                    {l10n.p3.li1}<Link href={'https://github.com/SirChardash'}
                                                             target={'_blank'}>{l10n.p3.li1Link}</Link>
                                </li>
                                <li>
                                    {l10n.p3.li2}<Link href={'https://etf.unibl.org/'}
                                                        target={'_blank'}>{l10n.p3.li2Link}</Link>
                                </li>
                                <li>{l10n.p3.li3}</li>
                            </ul>
                        </Box>
                    </Grid>
                    <Grid justifyContent={'space-evenly'}>
                        <Image
                            priority
                            src="/flag-english.png"
                            className={styles.round}
                            height={32}
                            width={32}
                        />
                        {' '}
                        <Image
                            src="/flag-serbian.png"
                            className={styles.round}
                            height={32}
                            width={32}
                        />
                    </Grid>
                </Grid>
            </Container>
        </Box>
    )
}