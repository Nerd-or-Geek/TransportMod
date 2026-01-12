#!/usr/bin/env python3
from PIL import Image, ImageDraw
import os

# Create item textures folder
item_textures_dir = r"src\main\resources\assets\flowpipes\textures\item"
os.makedirs(item_textures_dir, exist_ok=True)

# Define textures with colors
textures = {
    # Pipes - wood (brown)
    "item_pipe_wood": {"base": (139, 69, 19), "accent": (210, 105, 30)},
    # Pipes - iron (gray)
    "item_pipe_iron": {"base": (128, 128, 128), "accent": (192, 192, 192)},
    # Pipes - diamond (cyan)
    "item_pipe_diamond": {"base": (0, 255, 255), "accent": (0, 200, 200)},
    
    # Fluid pipes - wood (brown with blue)
    "fluid_pipe_wood": {"base": (139, 69, 19), "accent": (0, 100, 200)},
    # Fluid pipes - iron (gray with blue)
    "fluid_pipe_iron": {"base": (128, 128, 128), "accent": (0, 150, 255)},
    # Fluid pipes - diamond (cyan with darker blue)
    "fluid_pipe_diamond": {"base": (0, 255, 255), "accent": (0, 100, 200)},
    
    # Pumps - wood (brown with red)
    "pump_wood": {"base": (139, 69, 19), "accent": (255, 0, 0)},
    # Pumps - iron (gray with red)
    "pump_iron": {"base": (128, 128, 128), "accent": (255, 100, 100)},
    # Pumps - diamond (cyan with red)
    "pump_diamond": {"base": (0, 255, 255), "accent": (255, 0, 0)},
    
    # Sorters - wood (brown with yellow)
    "sorter_wood": {"base": (139, 69, 19), "accent": (255, 255, 0)},
    # Sorters - iron (gray with yellow)
    "sorter_iron": {"base": (128, 128, 128), "accent": (255, 255, 100)},
    # Sorters - diamond (cyan with yellow)
    "sorter_diamond": {"base": (0, 255, 255), "accent": (255, 255, 0)},
}

def create_texture(name, colors):
    """Create a 256x256 item texture with clear colors"""
    img = Image.new('RGBA', (256, 256), (0, 0, 0, 255))
    draw = ImageDraw.Draw(img)
    
    # Draw base color large rectangle
    draw.rectangle([20, 20, 236, 236], fill=colors["base"] + (255,))
    
    # Draw accent color border
    draw.rectangle([20, 20, 236, 50], fill=colors["accent"] + (255,))  # Top stripe
    draw.rectangle([20, 206, 236, 236], fill=colors["accent"] + (255,))  # Bottom stripe
    draw.rectangle([20, 20, 50, 236], fill=colors["accent"] + (255,))  # Left stripe
    draw.rectangle([206, 20, 236, 236], fill=colors["accent"] + (255,))  # Right stripe
    
    return img

# Generate all textures
for name, colors in textures.items():
    img = create_texture(name, colors)
    path = os.path.join(item_textures_dir, f"{name}.png")
    img.save(path)
    print(f"Created: {path}")

print("All item textures generated successfully!")
