# Set the base image to Node
FROM node:14 as build

# Set the working directory in Docker
WORKDIR /app

# Copy package.json and package-lock.json before other files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the code
COPY . ./

# Build the app
RUN npm run build

# Serve the app using a minimal nginx server
FROM nginx:alpine

# Create a custom Nginx configuration to listen on port 3000
RUN echo "server { listen 3000; location / { root /usr/share/nginx/html; try_files \$uri \$uri/ /index.html; } }" > /etc/nginx/conf.d/default.conf

# Copy the build folder from build stage into the root of nginx webhost
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 3000 for the app
EXPOSE 3000

# Command to run the app
CMD ["nginx", "-g", "daemon off;"]
