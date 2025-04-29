## Stage 1: Build the app
#FROM node:20 AS builder
#
## Install pnpm globally
#RUN npm install -g pnpm
#
## Set working directory
#WORKDIR /app
#
## Copy only the lockfile and package.json
#COPY pnpm-lock.yaml package.json tsconfig.json ./
#
## Install dependencies
#RUN pnpm install
#
#RUN npm i -g serve
#
## Copy the rest of your app
#COPY . .
#
## Build the Vite app
#RUN pnpm run build
#
#CMD [ "serve", "-s", "dist" ]

FROM node:18-alpine AS build
RUN npm install -g pnpm
WORKDIR /app
COPY pnpm-lock.yaml package.json tsconfig.json ./
RUN pnpm install
COPY . .
RUN pnpm run build

# Production Stage
FROM nginx:stable-alpine AS production
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

## Stage 2: Serve the built app
#FROM nginx:stable-alpine
#
## Copy built files from builder
#COPY --from=builder /app/dist /usr/share/nginx/html
#
## Copy custom nginx config for React Router
#COPY nginx.conf /etc/nginx/conf.d/default.conf
#
## Expose port
#EXPOSE 80
#
## Start nginx
#CMD ["nginx", "-g", "daemon off;"]

