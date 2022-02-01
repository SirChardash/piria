import '../styles/global.css'

export default function App({Component, pageProps, postData}) {
    return <Component {...pageProps} postData={postData}/>
}
