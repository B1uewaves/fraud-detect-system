# Use official lightweight Python image
FROM python:3.11-slim

# Set working directory
WORKDIR /app

# Copy only requirements first for caching
COPY requirements.txt .

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy source code
COPY . .

# Expose port if needed (adjust if your ML service listens on a port)
EXPOSE 5000

# Default command to run the service
CMD ["python", "app.py"]
