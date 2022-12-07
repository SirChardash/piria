FROM node:current-alpine

RUN addgroup -g 1001 -S nodejs
RUN adduser -S nextjs -u 1001

WORKDIR /app

COPY package*.json ./
COPY --chown=nextjs:nodejs .next ./.next
COPY public ./public
COPY ./next.config.js ./
COPY node_modules ./node_modules

USER nextjs

EXPOSE 3000
CMD npm run start