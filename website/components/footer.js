import {Container, Grid} from "@mui/material";
import Box from "@mui/material/Box";
import styles from '../styles/footer.module.css';
import Image from 'next/image'
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import Link from 'next/link'
import {useKeycloak} from "@react-keycloak/ssr";
import {logEvent} from "../lib/tracking";
import getUserId from "../lib/userId";

export default function Footer() {
    const {keycloak, initialized} = useKeycloak()
    const router = useRouter()
    const {locale} = router
    const {pathname, query, asPath} = router
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
                        <Link href={pathname} as={asPath} locale={'en'}>
                            <a onClick={() => logEvent(
                                initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
                                'footer',
                                null,
                                'language changed',
                                'en'
                            )}>
                                <Image priority
                                       src="/flag-english.png"
                                       className={styles.languageIcon}
                                       height={32}
                                       width={32}/>
                            </a>
                        </Link>
                        {' '}
                        <Link href={{pathname, query}} as={asPath} locale={'sr'}>
                            <a onClick={() => logEvent(
                                initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
                                'footer',
                                null,
                                'language changed',
                                'sr'
                            )}>
                                <Image src="/flag-serbian.png"
                                       className={styles.languageIcon}
                                       height={32}
                                       width={32}/>
                            </a>
                        </Link>
                    </Grid>
                </Grid>
            </Container>
        </Box>
    )
}