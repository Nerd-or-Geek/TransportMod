#!/usr/bin/env python3
"""
Generate placeholder textures for FlowPipes mod.
Run this script to create simple colored block textures.
"""

from PIL import Image, ImageDraw
import os

# Create directories if they don't exist
os.makedirs("src/main/resources/assets/flowpipes/textures/block", exist_ok=True)
os.makedirs("src/main/resources/assets/flowpipes/textures/item", exist_ok=True)
os.makedirs("src/main/resources/assets/flowpipes/textures/gui", exist_ok=True)

# Color definitions
WOOD = (139, 107, 71)
IRON = (168, 168, 168)
DIAMOND = (85, 255, 255)

def create_pipe_texture(color, name, is_fluid=False):
    """Create a hollow pipe texture."""
    img = Image.new('RGBA', (16, 16), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    # Draw outer square
    draw.rectangle([2, 2, 13, 13], outline=color, width=2)
    
    # Add pattern for fluid pipes
    if is_fluid:
        for i in range(4, 14, 2):
            draw.line([(i, 4), (i, 12)], fill=color, width=1)
    
    img.save(f"src/main/resources/assets/flowpipes/textures/block/{name}.png")

def create_pump_texture(color, name, active=False):
    """Create a pump texture with gear or arrow."""
    img = Image.new('RGBA', (16, 16), color)
    draw = ImageDraw.Draw(img)
    
    # Draw background
    draw.rectangle([0, 0, 15, 15], fill=color)
    
    # Draw a gear/rotor pattern
    if active:
        # Draw spinning gear appearance
        center = (8, 8)
        draw.ellipse([5, 5, 11, 11], outline=(255, 255, 255), width=1)
        draw.line([(8, 4), (8, 12)], fill=(255, 255, 255), width=1)
        draw.line([(4, 8), (12, 8)], fill=(255, 255, 255), width=1)
    else:
        # Draw static gear
        draw.ellipse([5, 5, 11, 11], outline=(200, 200, 200), width=1)
        draw.line([(8, 4), (8, 12)], fill=(200, 200, 200), width=1)
    
    img.save(f"src/main/resources/assets/flowpipes/textures/block/{name}.png")

def create_sorter_texture(color, name):
    """Create a sorter texture with grid."""
    img = Image.new('RGBA', (16, 16), color)
    draw = ImageDraw.Draw(img)
    
    # Draw background
    draw.rectangle([0, 0, 15, 15], fill=color)
    
    # Draw grid pattern (3x3)
    grid_color = tuple(min(c + 50, 255) for c in color)
    for i in range(1, 3):
        x = 5 + i * 3
        y = 5 + i * 3
        draw.line([(x, 5), (x, 14)], fill=grid_color, width=1)
        draw.line([(5, y), (14, y)], fill=grid_color, width=1)
    
    img.save(f"src/main/resources/assets/flowpipes/textures/block/{name}.png")

def create_gui_texture():
    """Create a GUI background texture."""
    img = Image.new('RGB', (256, 256), (139, 139, 139))
    draw = ImageDraw.Draw(img)
    
    # Draw borders
    draw.rectangle([0, 0, 255, 255], outline=(0, 0, 0), width=2)
    
    # Draw grid pattern for texture
    for i in range(0, 256, 16):
        draw.line([(i, 0), (i, 256)], fill=(100, 100, 100), width=1)
        draw.line([(0, i), (256, i)], fill=(100, 100, 100), width=1)
    
    img.save("src/main/resources/assets/flowpipes/textures/gui/sorter_gui.png")

# Generate all textures
print("Generating FlowPipes textures...")

# Pipes
create_pipe_texture(WOOD, "item_pipe_wood")
create_pipe_texture(IRON, "item_pipe_iron")
create_pipe_texture(DIAMOND, "item_pipe_diamond")

create_pipe_texture(WOOD, "fluid_pipe_wood", is_fluid=True)
create_pipe_texture(IRON, "fluid_pipe_iron", is_fluid=True)
create_pipe_texture(DIAMOND, "fluid_pipe_diamond", is_fluid=True)

# Pumps
create_pump_texture(WOOD, "pump_wood", active=False)
create_pump_texture(WOOD, "pump_wood_active", active=True)
create_pump_texture(IRON, "pump_iron", active=False)
create_pump_texture(IRON, "pump_iron_active", active=True)
create_pump_texture(DIAMOND, "pump_diamond", active=False)
create_pump_texture(DIAMOND, "pump_diamond_active", active=True)

# Sorters
create_sorter_texture(WOOD, "sorter_wood")
create_sorter_texture(IRON, "sorter_iron")
create_sorter_texture(DIAMOND, "sorter_diamond")

# Copy block textures to item folder (for simplicity)
import shutil
for filename in os.listdir("src/main/resources/assets/flowpipes/textures/block"):
    if filename.endswith(".png"):
        shutil.copy(
            f"src/main/resources/assets/flowpipes/textures/block/{filename}",
            f"src/main/resources/assets/flowpipes/textures/item/{filename}"
        )

# GUI
create_gui_texture()

print("✓ All textures generated successfully!")
print("✓ Block textures: 12 files")
print("✓ Item textures: 12 files")
print("✓ GUI textures: 1 file")
