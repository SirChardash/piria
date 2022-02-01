import {format, parseISO} from 'date-fns'
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function PrettyDate({dateString, dateOnly, timeOnly}) {
    const {locale} = useRouter()
    const dateFormat = fullL10n[locale].prettyDate.dateFormat
    const date = parseISO(dateString)
    const formatString = dateOnly
        ? dateFormat
        : timeOnly
            ? 'hh:mm\'h\''
            : 'hh:mm\'h\', ' + dateFormat
    return <time dateTime={dateString}>{format(date, formatString)}</time>
}